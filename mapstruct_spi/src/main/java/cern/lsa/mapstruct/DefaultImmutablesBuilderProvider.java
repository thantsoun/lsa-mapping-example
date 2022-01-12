package cern.lsa.mapstruct;

import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.PackageElement;
import javax.lang.model.element.TypeElement;
import org.mapstruct.ap.spi.ImmutablesBuilderProvider;

public class DefaultImmutablesBuilderProvider extends ImmutablesBuilderProvider {

    @Override
    protected TypeElement asImmutableElement(TypeElement typeElement) {
        Element enclosingElement = typeElement.getEnclosingElement();
        StringBuilder builderQualifiedName = new StringBuilder( typeElement.getQualifiedName().length() + 17 );
        if ( enclosingElement.getKind() == ElementKind.PACKAGE ) {
            builderQualifiedName.append( ( (PackageElement) enclosingElement ).getQualifiedName().toString() );
        }
        else {
            builderQualifiedName.append( ( (TypeElement) enclosingElement ).getQualifiedName().toString() );
        }

        if ( builderQualifiedName.length() > 0 ) {
            builderQualifiedName.append( "." );
        }

        builderQualifiedName.append( "Default" ).append( typeElement.getSimpleName() );
        return elementUtils.getTypeElement( builderQualifiedName );
    }

}