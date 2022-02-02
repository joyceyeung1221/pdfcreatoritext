package com.myob.bankfeeds.pdfcreatoritext

import com.itextpdf.text.pdf.BarcodeQRCode
import com.lowagie.text.Image
import org.apache.pdfbox.pdmodel.PDDocument
import org.apache.pdfbox.pdmodel.PDPage
import org.apache.pdfbox.pdmodel.PDResources
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject
import java.io.File


class OpenPDF {
    fun generateDocument(){
            val pDDocument: PDDocument = PDDocument.load(File("src/output/bank_app_libre_test.pdf"))
            val pDAcroForm = pDDocument.documentCatalog.acroForm
            var field = pDAcroForm.getField("account_holder_name")
            field.setValue("Thomas Haywood")
            var pdImage = PDImageXObject.createFromFile("src/output/3408.jpg", pDDocument)

            val page: PDPage = pDDocument.getPage(1)
            var resources: PDResources = page.getResources()
            for (xObjectName in resources.xObjectNames) {
                val xObject = resources.getXObject(xObjectName)
                if (xObject is PDImageXObject) {

                    val replacement_img = pdImage
                    resources.put(xObjectName, replacement_img);
                }
            }
        val barcodeQRCode = BarcodeQRCode("https://memorynotfound.com", 1000, 1000, null)
        val codeQrImage: com.itextpdf.text.Image = barcodeQRCode.image
        codeQrImage.scaleAbsolute(100F, 100F)
            pDDocument.save("src/output/test_output_openpdf.pdf")

            pDDocument.close()

    }
}