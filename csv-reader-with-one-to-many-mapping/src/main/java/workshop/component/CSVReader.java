package workshop.component;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.HeaderColumnNameMappingStrategy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import workshop.records.Address;
import workshop.records.Employee;

import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@Slf4j
@Component
public class CSVReader {

    private static final String ADDRESS_CSV = "address.csv";
    private static final String EMPLOYEE_CSV = "employee.csv";

    public <T> List<T> parse(Path path) throws Exception {
        String fileName = path.getFileName().toString();
        if (ADDRESS_CSV.equals(fileName)) {
            return convertCsvToList(path, Address.class);
        } else if (EMPLOYEE_CSV.equals(fileName)) {
            return convertCsvToList(path, Employee.class);
        } else {
            throw new IllegalArgumentException("wrong csv file");
        }
    }

    //https://stackoverflow.com/questions/61334683/convert-csv-file-into-objects-in-a-generic-way
    //http://opencsv.sourceforge.net/
    private <T> List<T> convertCsvToList(Path path, Class clazz) throws Exception {
        log.info("convertCsvToList() method start");
        HeaderColumnNameMappingStrategy mappingStrategy = new HeaderColumnNameMappingStrategy();
        mappingStrategy.setType(clazz);

        List<T> list;
        try (Reader reader = Files.newBufferedReader(path)) {
            CsvToBean csvToBean = new CsvToBeanBuilder(reader)
                    .withType(clazz)
                    .withMappingStrategy(mappingStrategy)
                    .build();
            list = csvToBean.parse();
        }
        return list;
    }
}
