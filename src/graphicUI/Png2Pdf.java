package graphicUI;

import java.io.FileOutputStream;

//com.lowagie...   old version
//com.itextpdf...  recent version
import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import java.io.File;

public class Png2Pdf {

	public Png2Pdf() {
	}

	public void convert(boolean scale, String subject, String filePath) {
		if (filePath != null && !"".equals(filePath)) {
			Document document = new Document();
			String output = "";
			filePath.toLowerCase();
			if (filePath.endsWith(".pdf.png")) {
				int a = filePath.lastIndexOf(".");
				if (a != -1)
					output = filePath.substring(0, a - 4) + ".pdf";

			} else {
				int a = filePath.lastIndexOf(".");
				if (a != -1) {
					output = filePath.substring(0, a) + ".pdf";
				} else {
					output = filePath + ".pdf";
				}
			}

			try {
				FileOutputStream fos = new FileOutputStream(output);
				PdfWriter writer = PdfWriter.getInstance(document, fos);
				writer.open();
				document.open();
				Image img = Image.getInstance(filePath);
				System.out.println("ass :" + document.getPageSize().getWidth()
						+ " , " + document.getPageSize().getHeight());
				img.scaleAbsolute(document.getPageSize().getWidth() * 7 / 8,
						img.getHeight());
				// img.scaleAbsoluteHeight(document.getPageSize().getWidth()-100);
				// img.setScaleToFitHeight(true);
				// img.setScaleToFitLineWhenOverflow(true);
				Paragraph p = new Paragraph(subject);
				p.setAlignment(Paragraph.ALIGN_CENTER);
				document.add(img);
				document.close();
				writer.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		remove(filePath);
	}

	private void remove(String filePath) {
		File f = new File(filePath);
		if (f.exists()) {
			f.delete();
		}
	}
}
