package com.myob.bankfeeds.pdfcreatoritext

import org.apache.pdfbox.pdmodel.PDDocument
import org.apache.pdfbox.pdmodel.PDPage
import org.apache.pdfbox.pdmodel.PDResources
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject
import java.io.File


class PDFBox {
    fun generateDocument(){
            val pDDocument: PDDocument = PDDocument.load(File("src/output/bank_app_libre_test.pdf"))
            val pDAcroForm = pDDocument.documentCatalog.acroForm
            var field = pDAcroForm.getField("account_holder_name")
            field.setValue("Thomas Haywood")
            var pdImage = PDImageXObject.createFromFile("src/output/3408.jpg", pDDocument)

            var page: PDPage = pDDocument.getPage(1)
            var resources: PDResources = page.getResources()
            for (xObjectName in resources.xObjectNames) {
                val xObject = resources.getXObject(xObjectName)
                if (xObject is PDImageXObject) {

                    val replacement_img = pdImage
                    resources.put(xObjectName, replacement_img);
                }
            }


            pDDocument.save("src/output/test_output_openpdf.pdf")

            pDDocument.close()

    }
 }