package com.myob.bankfeeds.pdfcreatoritext

import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
class PdfCreatorItextApplication

fun main(args: Array<String>) {
    PDFBox().generateDocument()
}
