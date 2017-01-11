package io.github.siscultural.utils;

import io.github.siscultural.entities.SystemPayment;
import io.github.siscultural.repositories.SystemPaymentRepository;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.util.Iterator;

/**
 * Created by natarajan on 11/01/17.
 */
@Service
public class SystemPaymentUtils {

    @Autowired
    private SystemPaymentRepository systemPaymentRepository;

    public SystemPaymentUtils(SystemPaymentRepository systemPaymentRepository) {

        this.systemPaymentRepository = systemPaymentRepository;

    }

    public void updateTable(InputStream file) throws IOException, InvalidFormatException {

//        org.apache.poi.ss.usermodel.Workbook workbook = WorkbookFactory.create(new File("/Users/natarajan/Downloads/forn2.xls"));

        org.apache.poi.ss.usermodel.Workbook workbook = WorkbookFactory.create(file);
        org.apache.poi.ss.usermodel.Sheet firstSheet = workbook.getSheetAt(0);

        Iterator<Row> iterator = firstSheet.iterator();

        //pula primeira linha
        iterator.next();

        while (iterator.hasNext()) {
            Row nextRow = iterator.next();

            if (nextRow.getCell(7).getStringCellValue().equals("FO")){

                String num_pgt = new BigDecimal(nextRow.getCell(0).getNumericCellValue()).toString();
                String num_cpf_cgc = null;
                if (nextRow.getCell(15) != null)
                    num_cpf_cgc = nextRow.getCell(15).getStringCellValue();
                double valor = nextRow.getCell(23).getNumericCellValue();


                SystemPayment systemPayment = new SystemPayment(new BigInteger(num_pgt), num_cpf_cgc, new BigDecimal(valor, MathContext.DECIMAL32));

                systemPaymentRepository.save(systemPayment);

            }

        }

        workbook.close();
    }
}
