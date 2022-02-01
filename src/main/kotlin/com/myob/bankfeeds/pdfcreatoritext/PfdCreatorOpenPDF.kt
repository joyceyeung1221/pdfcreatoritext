package com.myob.bankfeeds.pdfcreatoritext

import com.lowagie.text.*
import com.lowagie.text.pdf.PdfPCell
import com.lowagie.text.pdf.PdfPTable
import com.lowagie.text.pdf.PdfWriter
import java.awt.Color
import java.io.FileOutputStream


class PfdCreatorOpenPDF {
    fun generateDocument(Name: String){
        var document: Document = Document(PageSize.A4);

        PdfWriter.getInstance(document, FileOutputStream("src/output/ITextHelloWorld.pdf")).setPdfVersion(PdfWriter.PDF_VERSION_1_7)
        document.open()
        val whiteFont = Font(Font.HELVETICA, 18.toFloat(), Font.NORMAL, Color(255, 255, 200))
        var chunk: Chunk = Chunk("new world", whiteFont)
        var paragraph = Paragraph("This is right aligned text")
        paragraph.alignment = Element.ALIGN_RIGHT
        document.add(paragraph)
        paragraph = Paragraph("This is centered text ${Name}")
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
        val table = PdfPTable(2)
        table.setWidthPercentage(100.toFloat())
        val blue = Color(0, 0, 255)

        var cell = PdfPCell()
        cell.setBorderWidth(0.toFloat())
        cell.setBackgroundColor(blue)
        // here i try to change the alignment of text in the cell
        // here i try to change the alignment of text in the cell

        val p = Paragraph("test1", whiteFont)
        p.alignment = Element.ALIGN_MIDDLE
        cell.addElement(p)
        cell.setHorizontalAlignment(Element.ALIGN_CENTER)
        cell.setVerticalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell)

        cell = PdfPCell()
        cell.setVerticalAlignment(Element.ALIGN_CENTER);
        cell.setBorderWidth(0.toFloat())
        cell.setBackgroundColor(blue)
        cell.addElement(Paragraph("test2", whiteFont))
        table.addCell(cell)

        document.add(table)
        var img:Image = Image.getInstance("src/output/3408.jpg")
        img.scaleAbsolute(80f, 80f)
        img.setAbsolutePosition(400f, 400f)
        document.add(img)
        document.add(chunk)
        document.close()
        System.out.println("Done")
    }
}