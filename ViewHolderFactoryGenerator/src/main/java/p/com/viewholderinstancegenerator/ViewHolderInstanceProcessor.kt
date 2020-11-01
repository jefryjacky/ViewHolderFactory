package p.com.viewholderinstancegenerator

import com.google.auto.service.AutoService
import com.squareup.kotlinpoet.*
import me.eugeniomarletti.kotlin.metadata.KotlinClassMetadata
import me.eugeniomarletti.kotlin.metadata.KotlinMetadataUtils
import me.eugeniomarletti.kotlin.metadata.kotlinMetadata
import me.eugeniomarletti.kotlin.processing.KotlinAbstractProcessor
import p.com.viewholderinstanceannotation.ViewHolderAnnotation
import java.io.File
import javax.annotation.processing.Processor
import javax.annotation.processing.RoundEnvironment
import javax.annotation.processing.SupportedSourceVersion
import javax.lang.model.SourceVersion
import javax.lang.model.element.TypeElement
import javax.tools.Diagnostic

@AutoService(Processor::class)
@SupportedSourceVersion(SourceVersion.RELEASE_8)
class ViewHolderInstanceProcessor() :KotlinAbstractProcessor(), KotlinMetadataUtils {

    override fun getSupportedAnnotationTypes() = setOf(ANNOTATION.canonicalName)

    override fun process(annotations: Set<TypeElement>, roundEnv: RoundEnvironment): Boolean {
        val outputDir = generatedDir
        if(outputDir == null){
            messager.printMessage(Diagnostic.Kind.ERROR, "Cannot find generated output dir.")
            return false
        }

        val annotatedElements = roundEnv.getElementsAnnotatedWith(ANNOTATION)

        annotatedElements.forEach loop@{
            if(it.kotlinMetadata !is KotlinClassMetadata || it !is TypeElement){
                messager.printMessage(Diagnostic.Kind.WARNING, "$it is not a kotlin class")
                return@loop
            }
           generateCode(it, outputDir)
        }

        return true
    }

    private fun generateCode(element: TypeElement, outputDir: File){
        val layout = element.getAnnotation(ANNOTATION).layout

        val viewHolderPackageName = element.asClassName().packageName
        val viewHolderClassName = element.asClassName().simpleName
        val generatedClass = "${viewHolderClassName}Factory"
        val viewGroup = ClassName("android.view", "ViewGroup")
        val viewHolder = ClassName(viewHolderPackageName, viewHolderClassName)
        val layoutInflater = MemberName("android.view", "LayoutInflater")

        val companion = TypeSpec.companionObjectBuilder()
            .addFunction(FunSpec.builder("create")
                .addParameter("viewGroup", viewGroup)
                .returns(viewHolder)
                .addStatement("val view = %M.from(viewGroup.context).inflate(R.layout.$layout, viewGroup, false)", layoutInflater)
                .addStatement("return %T(view)", viewHolder)
                .build())
            .build()

        val factoryTypeSpec = TypeSpec.classBuilder(ClassName(viewHolderPackageName,generatedClass))
            .addType(companion)
            .build()

        FileSpec.builder(viewHolderPackageName, generatedClass)
            .addType(factoryTypeSpec)
            .build().writeTo(outputDir)
    }

    companion object{
        val ANNOTATION = ViewHolderAnnotation::class.java
    }
}