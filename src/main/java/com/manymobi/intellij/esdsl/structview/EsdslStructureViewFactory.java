package com.manymobi.intellij.esdsl.structview;

import com.intellij.ide.structureView.StructureViewBuilder;
import com.intellij.ide.structureView.StructureViewModel;
import com.intellij.ide.structureView.TreeBasedStructureViewBuilder;
import com.intellij.lang.PsiStructureViewFactory;
import com.intellij.openapi.editor.Editor;
import com.intellij.psi.PsiFile;
import com.manymobi.intellij.esdsl.psi.EsdslPSIFileRoot;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class EsdslStructureViewFactory implements PsiStructureViewFactory {
	@Nullable
	@Override
	public StructureViewBuilder getStructureViewBuilder(final PsiFile psiFile) {
		return new TreeBasedStructureViewBuilder() {
			@NotNull
			@Override
			public StructureViewModel createStructureViewModel(@Nullable Editor editor) {
				return new EsdslStructureViewModel((EsdslPSIFileRoot)psiFile);
			}
		};
	}
}
