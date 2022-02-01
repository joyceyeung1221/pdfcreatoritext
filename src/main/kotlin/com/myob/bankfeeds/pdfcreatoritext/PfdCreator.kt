package com.myob.bankfeeds.pdfcreatoritext

import com.itextpdf.text.*
import com.itextpdf.text.pdf.PdfWriter
import java.io.FileOutputStream


class PfdCreator {
    fun generateDocument(){
        var document: Document = Document(PageSize.A4);

        PdfWriter.getInstance(document, FileOutputStream("src/output/ITextHelloWorld.pdf")).setPdfVersion(PdfWriter.PDF_VERSION_1_7)
        document.open()
        var font: Font = FontFactory.getFont(FontFactory.COURIER, 16.toFloat(), BaseColor.BLACK)
        var chunk: Chunk = Chunk("new world", font)
        var paragraph = Paragraph("This is right aligned text")
        paragraph.alignment = Element.ALIGN_RIGHT
        document.add(paragraph)
        paragraph = Paragraph("This is centered text")
        paragraph.alignment = Element.ALIGN_CENTER
        document.add(paragraph)
        paragraph = Paragraph("This is left aligned text")
        paragraph.alignment = Element.ALIGN_LEFT
        document.add(paragraph)

        paragraph = Paragraph(
            "This is left aligned text with indentation"
        )
        paragraph.alignment = Element.ALIGN_LEFT
        paragraph.indentationLeft = 50f
        document.add(paragraph)
        var img:Image = Image.getInstance("src/output/3408.jpg")
        img.scaleAbsolute(50f, 50f)
        img.setAbsolutePosition(150f, 100f)
        document.add(img)
        document.add(chunk)
        document.close()
        System.out.println("Done")

    }
}