package com.manymobi.jetbrains.esdsl.line;

import com.intellij.codeInsight.daemon.RelatedItemLineMarkerInfo;
import com.intellij.codeInsight.daemon.RelatedItemLineMarkerProvider;
import com.intellij.codeInsight.navigation.NavigationGutterIconBuilder;
import com.intellij.openapi.editor.markup.GutterIconRenderer;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiMethod;
import com.intellij.psi.search.GlobalSearchScope;
import com.intellij.psi.search.PsiShortNamesCache;
import com.manymobi.jetbrains.esdsl.EsdslLanguage;
import com.manymobi.jetbrains.esdsl.Icons;
import com.manymobi.jetbrains.esdsl.psi.FunctionSubtree;
import org.antlr.intellij.adaptor.xpath.XPath;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.Objects;
import java.util.Optional;

/**
 * @author 梁建军
 * 创建日期： 2021/9/2
 * 创建时间： 下午12:18
 * @version 1.0
 * @since 1.0
 * 从 esdsl 文件点击连接到 java 文件
 */
public class EsdslLineMarkerProvider extends RelatedItemLineMarkerProvider {
    @Override
    protected void collectNavigationMarkers(@NotNull PsiElement element, @NotNull Collection<? super RelatedItemLineMarkerInfo<?>> result) {
        if (!(element instanceof FunctionSubtree)) {
            return;
        }

        String fileName = element.getContainingFile().getName();
        Collection<? extends PsiElement> all = XPath.findAll(EsdslLanguage.INSTANCE, element, "/esdsl/ID");
        for (PsiElement psiElement : all) {

            @NotNull PsiMethod[] methodsByName = PsiShortNamesCache.getInstance(element.getProject())
                    .getMethodsByName(psiElement.getText(), GlobalSearchScope.allScope(element.getProject()));
            for (PsiMethod psiMethod : methodsByName) {
                Optional.ofNullable(psiMethod.getContainingClass())
                        .map(psiClass1 -> psiClass1.getAnnotation("com.manymobi.esdsl.annotations.Mapper"))
                        .map(psiAnnotation -> psiAnnotation.findAttributeValue("value"))
                        .map(PsiElement::getText)
                        .map(s -> s.replace("\"", ""))
                        .map(s -> s.substring(s.lastIndexOf("/") + 1))
                        .filter(s -> Objects.equals(fileName, s))
                        .ifPresent(s -> {
                            NavigationGutterIconBuilder<PsiElement> builder =
                                    NavigationGutterIconBuilder.create(Icons.SAMPLE_ICON)
                                            .setAlignment(GutterIconRenderer.Alignment.CENTER)
                                            .setTarget(psiMethod);
                            result.add(builder.createLineMarkerInfo(psiElement));
                        });
            }
        }
    }
}
