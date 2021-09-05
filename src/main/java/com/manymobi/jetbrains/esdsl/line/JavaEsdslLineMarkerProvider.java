package com.manymobi.jetbrains.esdsl.line;

import com.intellij.codeInsight.daemon.RelatedItemLineMarkerInfo;
import com.intellij.codeInsight.daemon.RelatedItemLineMarkerProvider;
import com.intellij.codeInsight.navigation.NavigationGutterIconBuilder;
import com.intellij.openapi.editor.markup.GutterIconRenderer;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiMethod;
import com.intellij.psi.search.PsiShortNamesCache;
import com.manymobi.jetbrains.esdsl.EsdslLanguage;
import com.manymobi.jetbrains.esdsl.Icons;
import org.antlr.intellij.adaptor.xpath.XPath;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author 梁建军
 * 创建日期： 2021/9/2
 * 创建时间： 下午12:18
 * @version 1.0
 * @since 1.0
 * 从 java 文件中连接到 esdsl 文件中
 */
public class JavaEsdslLineMarkerProvider extends RelatedItemLineMarkerProvider {

    @Override
    protected void collectNavigationMarkers(@NotNull PsiElement element, @NotNull Collection<? super RelatedItemLineMarkerInfo<?>> result) {
        if (!(element instanceof PsiClass)) {
            return;
        }

        PsiClass psiClass = (PsiClass) element;

        PsiMethod[] methods = psiClass.getMethods();
        Map<String, PsiMethod> collect = Arrays.stream(methods)
                .collect(Collectors.toMap(PsiMethod::getName, o -> o));
        Optional.of(psiClass)
                .map(psiClass1 -> psiClass1.getAnnotation("com.manymobi.esdsl.annotations.Mapper"))
                .map(psiAnnotation -> psiAnnotation.findAttributeValue("value"))
                .map(PsiElement::getText)
                .map(s -> s.replace("\"", ""))
                .map(s -> s.substring(s.lastIndexOf("/") + 1))
                .map(s -> PsiShortNamesCache.getInstance(element.getProject())
                        .getFilesByName(s))
                .stream()
                .flatMap(Arrays::stream)
                .flatMap(psiFile ->
                        XPath.findAll(EsdslLanguage.INSTANCE, psiFile, "/esdslarray/esdsl/ID").stream())
                .forEach(psiElement -> {
                    PsiMethod psiMethod = collect.get(psiElement.getText());
                    if (psiMethod != null) {
                        NavigationGutterIconBuilder<PsiElement> builder =
                                NavigationGutterIconBuilder.create(Icons.SAMPLE_ICON)
                                        .setAlignment(GutterIconRenderer.Alignment.CENTER)
                                        .setTarget(psiElement);
                        result.add(builder.createLineMarkerInfo(psiMethod.getNameIdentifier()));
                    }

                });

    }

}
