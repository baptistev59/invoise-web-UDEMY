package com.mycompany.invoise.invoiseweb.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mycompany.invoise.entity.Invoice;
import com.mycompany.invoise.service.InvoiceServiceInterface;

@RestController
@RequestMapping("/invoice")
public class InvoiceRessource {

	@Autowired
	private InvoiceServiceInterface invoiceService;

	public InvoiceServiceInterface getInvoiceService() {
		return invoiceService;
	}

	public void setInvoiceService(InvoiceServiceInterface invoiceService) {
		this.invoiceService = invoiceService;
	}

	@PostMapping
	public Invoice create(@RequestBody Invoice invoice) {
		System.out.println("L'interface create a été utilisée !");
		return invoiceService.createInvoice(invoice);
	}

	@GetMapping
	public Iterable<Invoice> list() {
		System.out.println("La méthode List a été invoquée !");
		return invoiceService.getInvoiceList();

	}
//
//	@GetMapping("/create-form")
//	public String displayInvoiceCreateForm(@ModelAttribute InvoiceForm invoice) {
//		return "invoice-create-form";
//
//	}

	@GetMapping("/{id}")
	public Invoice get(@PathVariable("id") String number) {
		System.out.println("La méthode get a été invoquée !");
		return invoiceService.getInvoiceByNumber(number);
	}

}
