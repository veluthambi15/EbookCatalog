# Ebook Catalog Application

This is a simple Spring Boot application for managing an ebook catalog.

## Getting Started

These instructions will help you run the application on your local machine.

### Prerequisites

- Java 8 or higher
- Maven

## Running the Application using command prompt

Clone the repository:

   git clone https://github.com/veluthambi15/EbookCatalog.git
   
Navigate to the project directory:

	cd EbookCatalog
	
Build the project using Maven:

	mvn clean install

Run the application:

	mvn spring-boot:run


## API Endpoints

The application will be accessible at http://localhost:8080

Add new ebook to the catalog :

	POST : http://localhost:8080/ebooks

	{
		"author": "Velu",
		"title": "Finnish Language",
		"format": "Pdf"
	}

Fetch all ebooks:

	GET : http://localhost:8080/ebooks
	
Fetch ebook by ID:

	GET : http://localhost:8080/ebooks/{ebook_id}
	
Update attributes of a book:

	 PUT : http://localhost:8080/ebooks/{ebook_id}

	{
		"author": "Praveen",
		"title": "English Language",
		"format": "Online"
	}

Delete a book by ID:

	DELETE : http://localhost:8080/ebooks/{ebook_id}
	

## Access the Endpoints using Command Prompt

To add a new Ebook through Command prompt - Open a new Command Prompt:

	curl -X POST -H "Content-Type: application/json" -d '{"author":"Veluthambi","title":"Finnish Language","format":"Pdf"}' http://localhost:8080/ebooks
		
Or:

	curl -X POST -H "Content-Type: application/json" -d "{\"author\":\"Veluthambi\",\"title\":\"Finnish Language\",\"format\":\"Pdf\"}" http://localhost:8080/ebooks
	
To fetch all the Ebooks through Command prompt:

	curl http://localhost:8080/ebooks
	
To fetch the ebook by Id through Command prompt:
	
	curl http://localhost:8080/ebooks/{ebook_id}
	
To update attributes of a ebook through Command prompt:

	curl -X PUT -H "Content-Type: application/json" -d '{"author":"Praveen","title":"English Language","format":"Online"}' http://localhost:8080/ebooks/{ebook_id}
	
Or:

	curl -X PUT -H "Content-Type: application/json" -d "{\"author\":\"Praveen\",\"title\":\"English Language\",\"format\":\"Online\"}" http://localhost:8080/ebooks/{ebook_id}
	
To Delete a book by ID through Command prompt:

	curl -X DELETE http://localhost:8080/ebooks/{ebook_id}
	
## Testing

Unit Tests - Run unit tests using the following Maven command:
 
	mvn test
	
Integration Tests - Run integration tests using the following Maven command:

	mvn verify


## Built With

- Spring Boot
- Maven

## Authors

- Veluthambi Mullaivalavan

## License

- This project is licensed under the MIT License - see the LICENSE file for details.
