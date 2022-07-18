package com.mycompany.invoise.invoiseweb.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mycompany.invoise.entity.Invoice;
import com.mycompany.invoise.invoiseweb.form.InvoiceForm;
import com.mycompany.invoise.service.InvoiceServiceInterface;

@Controller
@RequestMapping("/invoice")
public class InvoiceControllerWeb {

	@Autowired
	private InvoiceServiceInterface invoiceService;

	public InvoiceServiceInterface getInvoiceService() {
		return invoiceService;
	}

	public void setInvoiceService(InvoiceServiceInterface invoiceService) {
		this.invoiceService = invoiceService;
	}

	@PostMapping("/create")
	public String createInvoice(@Valid @ModelAttribute InvoiceForm invoiceForm, BindingResult results) {
		System.out.println("L'interface Web a été utilisée !");
		if (results.hasErrors()) {
			return "invoice-create-form";
		} else {
			Invoice invoice = new Invoice();
			//invoice.setCustomerName(invoiceForm.getCustomerName());
			invoice.setOrderNumber(invoiceForm.getOrderNumber());
			invoiceService.createInvoice(invoice);
			return "invoice-created";
		}	
	}

	@GetMapping("/home")
	public String displayHome(Model model) {
		System.out.println("La méthode displayHome a été invoquée !");
		model.addAttribute("invoices", invoiceService.getInvoiceList());
		return "invoice-home";

	}

	//@GetMapping("/{id}")
	public String displayInvoice(@PathVariable("id") String number, Model model) {
		System.out.println("La méthode displayInvoice a été invoquée !");
		model.addAttribute("invoice", invoiceService.getInvoiceByNumber(number));
		return "invoice-details";

	}

	@GetMapping("/create-form")
	public String displayInvoiceCreateForm(@ModelAttribute InvoiceForm invoice) {
		return "invoice-create-form";

	}

}
