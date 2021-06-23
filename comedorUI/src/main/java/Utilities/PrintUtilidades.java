/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilities;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.itextpdf.html2pdf.ConverterProperties;
import com.itextpdf.html2pdf.HtmlConverter;
import com.itextpdf.kernel.pdf.PdfWriter;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Base64;
import javax.imageio.ImageIO;
import javax.xml.bind.DatatypeConverter;
import net.sourceforge.barbecue.Barcode;
import net.sourceforge.barbecue.BarcodeFactory;
import net.sourceforge.barbecue.BarcodeImageHandler;
import static org.bouncycastle.asn1.cms.CMSObjectIdentifiers.data;

/**
 *
 * @author alex4
 */
public class PrintUtilidades {

    public PrintUtilidades() {
    }

    public BufferedImage generateQRCodeImage(String barcodeText) throws Exception {
        QRCodeWriter barcodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix
                = barcodeWriter.encode(barcodeText, BarcodeFormat.QR_CODE, 200, 200);

        return MatrixToImageWriter.toBufferedImage(bitMatrix);
    }

    public BufferedImage generateEAN13BarcodeImage(String barcodeText) throws Exception {
        Barcode barcode = BarcodeFactory.createEAN13(barcodeText);
        return BarcodeImageHandler.getImage(barcode);
    }

    public String qrCodeStr(String data) throws IOException, Exception {
        BufferedImage bi = generateQRCodeImage(data);
        ByteArrayOutputStream out1 = new ByteArrayOutputStream();
        ImageIO.write(bi, "PNG", out1);
        String img = DatatypeConverter.printBase64Binary(out1.toByteArray());
        return img;
    }

    public String transformHTMltoPDF(String HTML) throws FileNotFoundException, IOException {
        File file = new File("C:\\Users\\alex4\\Documents\\string-to-pdf.pdf");
        ConverterProperties converterProperties = new ConverterProperties();
        converterProperties.setBaseUri("https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css");

        // Open PDF document in write mode
        PdfWriter pdfWriter = new PdfWriter(file);
        HtmlConverter.convertToPdf(HTML, pdfWriter, converterProperties);

        byte[] fileContent = Files.readAllBytes(file.toPath());
        return Base64.getEncoder().encodeToString(fileContent);
    }

}
