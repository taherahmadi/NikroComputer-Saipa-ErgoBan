package graphicUI;

import java.io.FileOutputStream;
import java.io.IOException;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.RandomAccessFileOrArray;
import com.itextpdf.text.pdf.codec.TiffImage;

public class Tiff2Pdf {

	/**
	 * @param args
	 * @throws DocumentException
	 * @throws IOException
	 */
	public static void main(String[] args) throws DocumentException,
			IOException {

		String imgeFilename = "/home/saurabh/Downloads/image.tif";

		Document document = new Document();
		PdfWriter writer = PdfWriter.getInstance(
				document,
				new FileOutputStream("/home/saurabh/Desktop/out"
						+ Math.random() + ".pdf"));
		writer.setStrictImageSequence(true);
		document.open();

		document.add(new Paragraph("Multipages tiff file"));
		Image image;
		@SuppressWarnings("deprecation")
		RandomAccessFileOrArray ra = new RandomAccessFileOrArray(imgeFilename);
		int pages = TiffImage.getNumberOfPages(ra);
		for (int i = 1; i <= pages; i++) {
			image = TiffImage.getTiffImage(ra, i);
			Rectangle pageSize = new Rectangle(image.getWidth(),
					image.getHeight());
			document.setPageSize(pageSize);
			document.add(image);
			document.newPage();
		}

		document.close();

	}

}