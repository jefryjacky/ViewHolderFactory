package com.jefryjacky.viewholderinstanceannotation


@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.SOURCE)
@MustBeDocumented
annotation class ViewHolderAnnotation(
    val layout: String
)