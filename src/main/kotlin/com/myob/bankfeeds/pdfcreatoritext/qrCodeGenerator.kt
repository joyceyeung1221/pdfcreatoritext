package com.myob.bankfeeds.pdfcreatoritext

import com.google.zxing.BarcodeFormat
import com.google.zxing.common.BitMatrix
import com.google.zxing.qrcode.QRCodeWriter
import java.awt.Color
import java.awt.Graphics2D
import java.awt.image.BufferedImage
import java.io.ByteArrayOutputStream
import javax.imageio.ImageIO

class qrCodeGenerator {
    fun generateQrCode(text: String): ByteArray? {
        val byteStream = ByteArrayOutputStream()
        ImageIO.write(getQrImage(text), "png", byteStream)
        return byteStream.toByteArray()
    }

    fun getQrImage(text: String?): BufferedImage? {
        val writer = QRCodeWriter()
        val image = BufferedImage(300, 300, BufferedImage.TYPE_INT_RGB)
        draw(getGraphics(image), writer.encode(text, BarcodeFormat.QR_CODE, 300 , 300))
        return image
    }
    private fun draw(graphics: Graphics2D, matrix: BitMatrix) {
        for (i in 0 .. matrix.getWidth()) {
            for (j in 0 .. matrix.getHeight()) {
                if (matrix.get(i, j)) {
                    graphics.fillRect(i, j, 1, 1)
                }
            }
        }
    }

    private fun getGraphics(image: BufferedImage): Graphics2D {
        val graphics = image.createGraphics()
        graphics.background = Color.WHITE
        graphics.clearRect(0, 0, image.width, image.height)
        graphics.color = Color.BLACK
        return graphics
    }
}