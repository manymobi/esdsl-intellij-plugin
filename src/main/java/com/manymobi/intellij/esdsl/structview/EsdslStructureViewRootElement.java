package com.manymobi.intellij.esdsl.structview;

import com.intellij.navigation.ItemPresentation;
import com.intellij.psi.PsiFile;
import org.jetbrains.annotations.NotNull;

public class EsdslStructureViewRootElement extends EsdslStructureViewElement {

    public EsdslStructureViewRootElement(PsiFile element) {
        super(element);
    }

    @NotNull
    @Override
    public ItemPresentation getPresentation() {
        return new EsdslRootPresentation((PsiFile) element);
    }
}
