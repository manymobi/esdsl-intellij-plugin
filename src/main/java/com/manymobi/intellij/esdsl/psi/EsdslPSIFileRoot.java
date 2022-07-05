package com.manymobi.intellij.esdsl.psi;

import com.intellij.extapi.psi.PsiFileBase;
import com.intellij.openapi.fileTypes.FileType;
import com.intellij.psi.FileViewProvider;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiNamedElement;
import com.manymobi.intellij.esdsl.EsdslFileType;
import com.manymobi.intellij.esdsl.EsdslLanguage;
import org.antlr.intellij.adaptor.SymtabUtils;
import org.antlr.intellij.adaptor.psi.ScopeNode;
import com.manymobi.intellij.esdsl.Icons;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class EsdslPSIFileRoot extends PsiFileBase implements ScopeNode {
    public EsdslPSIFileRoot(@NotNull FileViewProvider viewProvider) {
        super(viewProvider, EsdslLanguage.INSTANCE);
    }

    @NotNull
    @Override
    public FileType getFileType() {
        return EsdslFileType.INSTANCE;
    }

    @Override
    public String toString() {
        return "Esdsl Language file";
    }

    @Override
    public Icon getIcon(int flags) {
        return Icons.SAMPLE_ICON;
    }

	/** Return null since a file scope has no enclosing scope. It is
	 *  not itself in a scope.
	 */
	@Override
	public ScopeNode getContext() {
		return null;
	}

	@Nullable
	@Override
	public PsiElement resolve(PsiNamedElement element) {
//		System.out.println(getClass().getSimpleName()+
//		                   ".resolve("+element.getName()+
//		                   " at "+Integer.toHexString(element.hashCode())+")");
		System.out.println(element);
		if ( element.getParent() instanceof CallSubtree ) {
			return SymtabUtils.resolve(this, EsdslLanguage.INSTANCE,
			                           element, "/esdslarray/esdsl/ID");
		}
		return SymtabUtils.resolve(this, EsdslLanguage.INSTANCE,
		                           element, "/esdslarray/esdsl/ID");
	}
}
