package com.myob.bankfeeds.pdfcreatoritext

import com.google.zxing.BarcodeFormat
import com.google.zxing.EncodeHintType
import com.google.zxing.client.j2se.MatrixToImageConfig
import com.google.zxing.client.j2se.MatrixToImageWriter
import com.google.zxing.common.BitMatrix
import com.google.zxing.qrcode.QRCodeWriter
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel
import java.awt.Color
import java.awt.Graphics2D
import java.awt.image.BufferedImage
import java.io.ByteArrayOutputStream
import java.nio.ByteBuffer
import java.nio.charset.Charset
import java.util.*
import javax.imageio.ImageIO


class QrCodeService {
    fun generateQrCode(): ByteArray? {
        val byteStream = ByteArrayOutputStream()
        ImageIO.write(getQrImage(), "png", byteStream)
        return byteStream.toByteArray()
    }

    fun getQrImage(): BufferedImage {
        val text = "1|MYOB|LxNUUvfLI0xDwZG7uIcHwuQDbErFagM2Lvyy5gRDY0qV45y4bSm1pt8uJe+Np4tqoZFDLxjka1Dl0mc1P67ARs16QtmBvPyPLRYisI4ZxP1BcsbonF4Cy8p35HtyrnP9wVp+QEl/P5aGgN7XuoGjfoPc79g8kGL4ktuzFP4yxBM=|anGutDC3nOjRdiPX9+SkqqacQJ7aoYywqXreobjQSQE3VT8V3TDNCoOCRzTI/G4pk0Fd0y+sr1MRN7v+Ye32QejPBK9ycjUsnJHOfdEGSsNhj4RDxr/PqOTL0Fqo7YB3ybSIzVytaA+AzwmyG/5hyR9CimgDE+eudLJxR4eGIaU=|End"
        val hintMap = Hashtable<EncodeHintType, Any>()
        hintMap.put(EncodeHintType.QR_VERSION, 12)

        val writer = QRCodeWriter()
        val image = BufferedImage(300, 300, BufferedImage.TYPE_INT_RGB)
        draw(getGraphics(image), writer.encode(text, BarcodeFormat.QR_CODE, 300 , 300, hintMap))
        return image
    }

    private fun draw(graphics: Graphics2D, matrix: BitMatrix) {
        for (i in 0 .. matrix.getWidth() -1 ) {
            for (j in 0 .. matrix.getHeight() -1 ) {
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

    fun drawQR12():BufferedImage {
        val text = "1|MYOB|LxNUUvfLI0xDwZG7uIcHwuQDbErFagM2Lvyy5gRDY0qV45y4bSm1pt8uJe+Np4tqoZFDLxjka1Dl0mc1P67ARs16QtmBvPyPLRYisI4ZxP1BcsbonF4Cy8p35HtyrnP9wVp+QEl/P5aGgN7XuoGjfoPc79g8kGL4ktuzFP4yxBM=|anGutDC3nOjRdiPX9+SkqqacQJ7aoYywqXreobjQSQE3VT8V3TDNCoOCRzTI/G4pk0Fd0y+sr1MRN7v+Ye32QejPBK9ycjUsnJHOfdEGSsNhj4RDxr/PqOTL0Fqo7YB3ybSIzVytaA+AzwmyG/5hyR9CimgDE+eudLJxR4eGIaU=|End"
        val hintMap = Hashtable<EncodeHintType, Any>()
        hintMap.put(EncodeHintType.QR_VERSION, 11)
        hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);

        val writer = QRCodeWriter()

        val bitMatrix = writer.encode(text, BarcodeFormat.QR_CODE, 300, 300, hintMap)
        val config = MatrixToImageConfig(MatrixToImageConfig.BLACK, MatrixToImageConfig.WHITE)
        return MatrixToImageWriter.toBufferedImage(bitMatrix, config)
    }

    fun drawQR11():BufferedImage {
        val text = "1|MYOB|LxNUUvfLI0xDwZG7uIcHwuQDbErFagM2Lvyy5gRDY0qV45y4bSm1pt8uJe+Np4tqoZFDLxjka1Dl0mc1P67ARs16QtmBvPyPLRYisI4ZxP1BcsbonF4Cy8p35HtyrnP9wVp+QEl/P5aGgN7XuoGjfoPc79g8kGL4ktuzFP4yxBM=|anGutDC3nOjRdiPX9+SkqqacQJ7aoYywqXreobjQSQE3VT8V3TDNCoOCRzTI/G4pk0Fd0y+sr1MRN7v+Ye32QejPBK9ycjUsnJHOfdEGSsNhj4RDxr/PqOTL0Fqo7YB3ybSIzVytaA+AzwmyG/5hyR9CimgDE+eudLJxR4eGIaU=|End"
        val hintMap = Hashtable<EncodeHintType, Any>()
        hintMap.put(EncodeHintType.QR_VERSION, 11)
        hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
        hintMap.put(EncodeHintType.CHARACTER_SET,"ISO-8859-1")

        var iso: Charset = Charset.forName("ISO-8859-1")
        val outputBuffer: ByteBuffer = iso.encode(text)
        val outputData: ByteArray = outputBuffer.array()
        val writer = QRCodeWriter()

        val bitMatrix = writer.encode(outputData.toString(), BarcodeFormat.QR_CODE, 300, 300, hintMap)
        val config = MatrixToImageConfig(MatrixToImageConfig.BLACK, MatrixToImageConfig.WHITE)
        return MatrixToImageWriter.toBufferedImage(bitMatrix, config)
    }
}