/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.comedor.utilidades;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import javax.imageio.ImageIO;
import javax.xml.bind.DatatypeConverter;

/**
 *
 * @author alex4
 */
public class BarCode {

    public BarCode() {
    }

    private BufferedImage generateQRCodeImage(String barcodeText) throws Exception {
        QRCodeWriter barcodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix
                = barcodeWriter.encode(barcodeText, BarcodeFormat.QR_CODE, 200, 200);

        return MatrixToImageWriter.toBufferedImage(bitMatrix);
    }

    public String getQRCode(String data) throws Exception {
        BufferedImage bi = generateQRCodeImage(data);
        ByteArrayOutputStream out1 = new ByteArrayOutputStream();
        ImageIO.write(bi, "PNG", out1);
        String img = DatatypeConverter.printBase64Binary(out1.toByteArray());
        return "data:image/png;base64, " + img;
    }
    
    public String getOnlyQRCode(String data) throws Exception {
        BufferedImage bi = generateQRCodeImage(data);
        ByteArrayOutputStream out1 = new ByteArrayOutputStream();
        ImageIO.write(bi, "PNG", out1);
        String img = DatatypeConverter.printBase64Binary(out1.toByteArray());
        return img;
    }

}
