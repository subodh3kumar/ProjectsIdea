package workshop.component;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.HeaderColumnNameMappingStrategy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import workshop.entity.CsvFeedEntity;
import workshop.models.AddressCsvBean;
import workshop.models.EmployeeCsvBean;
import workshop.repository.CsvFeedRepository;

import javax.transaction.Transactional;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Component
public class CsvReader {

    private static final String ADDRESS_CSV = "address.csv";
    private static final String EMPLOYEE_CSV = "employee.csv";

    private final CsvFeedRepository repository;

    public CsvReader(CsvFeedRepository repository) {
        this.repository = repository;
    }

    public void parse(Path path) throws Exception {
        String fileName = path.getFileName().toString();
        if (ADDRESS_CSV.equals(fileName)) {
            List<AddressCsvBean> addressCsvBeanList = convertCsvToList(path, AddressCsvBean.class);
            save(addressCsvBeanList, null);
        } else if (EMPLOYEE_CSV.equals(fileName)) {
            List<EmployeeCsvBean> employeeCsvBeanList = convertCsvToList(path, EmployeeCsvBean.class);
            save(null, employeeCsvBeanList);
        } else {
            throw new IllegalArgumentException("wrong csv file");
        }
    }

    @Transactional
    private void save(List<AddressCsvBean> addressCsvBeanList, List<EmployeeCsvBean> employeeCsvBeanList) {
        CsvFeedEntity feed = new CsvFeedEntity();
        feed.setCreatedBy("Subodh Kumar");
        feed.setTimestamp(LocalDateTime.now());
        if (!CollectionUtils.isEmpty(addressCsvBeanList)) {
            convert(addressCsvBeanList)
            feed.setAddressEntityList(addressCsvBeanList);
        }
        if (!CollectionUtils.isEmpty(employeeCsvBeanList)) {
            feed.setEmployeeEntityList(employeeCsvBeanList);
        }
        repository.save(feed);
    }

    //https://stackoverflow.com/questions/61334683/convert-csv-file-into-objects-in-a-generic-way
    //http://opencsv.sourceforge.net/
    private <T> List<T> convertCsvToList(Path path, Class<T> clazz) throws Exception {
        log.info("convertCsvToList() method start");
        HeaderColumnNameMappingStrategy<T> mappingStrategy = new HeaderColumnNameMappingStrategy<>();
        mappingStrategy.setType(clazz);

        List<T> list;
        try (Reader reader = Files.newBufferedReader(path)) {
            CsvToBean<T> csvToBean = new CsvToBeanBuilder<T>(reader)
                    .withType(clazz)
                    .withMappingStrategy(mappingStrategy)
                    .build();
            list = csvToBean.parse();
        }
        return list;
    }
}
