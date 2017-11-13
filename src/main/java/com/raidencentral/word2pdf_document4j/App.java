package com.raidencentral.word2pdf_document4j;

import java.io.File;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import com.documents4j.api.DocumentType;
import com.documents4j.api.IConverter;
import com.documents4j.job.LocalConverter;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) throws InterruptedException, ExecutionException, Exception {
		File wordFile = new File("D:/temp/ContractGenerated.docx"), target = new File("D:/temp/contract_pdf.pdf");
		IConverter converter = LocalConverter.builder().baseFolder(new File("D:/temp/document4j_work_do_not_delete"))
				.workerPool(20, 25, 2, TimeUnit.SECONDS).processTimeout(5, TimeUnit.SECONDS).build();
		Future<Boolean> conversion = converter.convert(wordFile).as(DocumentType.MS_WORD).to(target)
				.as(DocumentType.PDF)
				// .prioritizeWith(1000) // optional
				.schedule();
		if (conversion.get() == false) {
			throw new Exception("Office2PDF conversion failed");
		}
		System.out.println("finished");
	}
}
