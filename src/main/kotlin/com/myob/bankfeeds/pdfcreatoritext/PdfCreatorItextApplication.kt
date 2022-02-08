package com.myob.bankfeeds.pdfcreatoritext

import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
class PdfCreatorItextApplication

fun main(args: Array<String>) {
    val pdfBox = PDFBox()
    pdfBox.generateDocument()
    val image = QrCodeService().drawQR11()
//    val image = QrCodeService().drawQR12()
    pdfBox.generateQRcode(image)
}
