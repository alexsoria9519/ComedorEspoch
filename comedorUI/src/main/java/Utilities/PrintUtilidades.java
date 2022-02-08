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

import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.VerticalAlignment;

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

    public String transformHTMltoPDF(String HTML) throws FileNotFoundException, IOException, Exception {
        File fileOr = new File("C:\\Users\\alex4\\Documents\\string-to-pdf.pdf"); // Pdf Convertido de HTML
        File fileGen = new File("C:\\Users\\alex4\\Documents\\string-to-pdf-generate.pdf");// Pdf agregado el footer de pie de pagina
        ConverterProperties converterProperties = new ConverterProperties();

        // Open PDF document in write mode
        PdfWriter pdfWriter = new PdfWriter(fileOr);
        HtmlConverter.convertToPdf(HTML, pdfWriter, converterProperties);

        manipulatePdf(fileOr, fileGen);

        byte[] fileContent = Files.readAllBytes(fileGen.toPath()); // Obtener el archivo PDF
        return Base64.getEncoder().encodeToString(fileContent); // Retornar archivo PDF para descarga
    }

    // Esta funcion es para agregar el pie de pagina de la cantidad de paginas del documento
    protected void manipulatePdf(File file, File fileGen) throws Exception {
        try {
            PdfDocument pdfDoc = new PdfDocument(new PdfReader(file), new PdfWriter(fileGen));
            Document doc = new Document(pdfDoc);

            int numberOfPages = pdfDoc.getNumberOfPages();
            for (int i = 1; i <= numberOfPages; i++) {
                Rectangle pageSize = pdfDoc.getFirstPage().getPageSize();
                // Write aligned text to the specified by parameters point
                doc.showTextAligned(new Paragraph(String.format("pagina %s de %s", i, numberOfPages)),
                        pageSize.getWidth() - 60, 30, i, TextAlignment.RIGHT, VerticalAlignment.BOTTOM, 0);
            }

            doc.close();
        } catch (Exception ex) {
            System.out.println("Utilities.PrintUtilidades.manipulatePdf() " + ex);
        }
    }
    
}
