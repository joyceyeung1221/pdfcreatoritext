package com.myob.bankfeeds.pdfcreatoritext
import org.apache.pdfbox.pdmodel.PDDocument
import org.apache.pdfbox.pdmodel.PDPage
import org.apache.pdfbox.pdmodel.PDPageContentStream
import org.apache.pdfbox.pdmodel.PDResources
import org.apache.pdfbox.pdmodel.graphics.image.LosslessFactory
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject
import java.awt.image.BufferedImage
import java.io.File

class PDFBox {
    fun generateDocument(){
            val pDDocument: PDDocument = PDDocument.load(File("src/input/bank_app_libre_test.pdf"))
            val pDAcroForm = pDDocument.documentCatalog.acroForm
            var field = pDAcroForm.getField("account_holder_name")
        field.setValue("MAGIC")
            var pdImage = PDImageXObject.createFromFile("src/input/sampleimage.jpg", pDDocument)

            var page: PDPage = pDDocument.getPage(1)
            var resources: PDResources = page.getResources()
            for (xObjectName in resources.xObjectNames) {
                val xObject = resources.getXObject(xObjectName)
                if (xObject is PDImageXObject) {

                    val replacement_img = pdImage
                    resources.put(xObjectName, replacement_img);
                }
            }

            pDDocument.save("src/output/output_pdfbox.pdf")

            pDDocument.close()
    }
    fun generateQRcode(image: BufferedImage){

        var doc= PDDocument()
        var newpage = PDPage()
        doc.addPage(newpage)

        val pdImageXObject = LosslessFactory.createFromImage(doc, image)
        val contentStream = PDPageContentStream(doc, newpage, true, false)
        contentStream.drawImage(
            pdImageXObject,
            1f,
            480f,
            (image.width  ).toFloat(),
            (image.height ).toFloat()
        )
        contentStream.close()
        doc.save("src/output/output_qr.pdf")
        doc.close()
    }
 }