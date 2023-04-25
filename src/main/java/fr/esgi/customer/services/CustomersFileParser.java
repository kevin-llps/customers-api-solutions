package fr.esgi.customer.services;

import fr.esgi.customer.beans.Customer;
import fr.esgi.customer.exception.CustomersParsingException;
import fr.esgi.customer.exception.CustomersResourceNotFoundException;
import fr.esgi.customer.exception.MalFormattedCustomersResourceUriException;
import jakarta.enterprise.context.ApplicationScoped;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static fr.esgi.customer.csv.CsvProperties.HEADERS;

@ApplicationScoped
public class CustomersFileParser {

    public List<Customer> parse(String filename) {
        URL resource = getClass().getClassLoader().getResource(filename);

        if (resource == null) {
            throw new CustomersResourceNotFoundException(filename + " n'a pas été trouvé");
        }

        CSVFormat csvFormat = CSVFormat.DEFAULT.builder()
                .setHeader(HEADERS)
                .setSkipHeaderRecord(true)
                .build();

        try (BufferedReader bufferedReader = Files.newBufferedReader(Paths.get(resource.toURI()));
             CSVParser parser = new CSVParser(bufferedReader, csvFormat)) {

            return parser.stream()
                    .filter(csvRecord -> csvRecord.size() == HEADERS.length)
                    .map((CSVRecord csvRecord) -> Customer.create(csvRecord, HEADERS))
                    .toList();

        } catch (URISyntaxException e) {
            throw new MalFormattedCustomersResourceUriException("L'URI du fichier " + filename + " est invalide", e);
        } catch (IOException e) {
            throw new CustomersParsingException("Une erreur a eu lieu durant le parsing du fichier " + filename, e);
        }
    }

}
