package com.jgartdesign.dsmeta.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.jgartdesign.dsmeta.entities.Sale;
import com.jgartdesign.dsmeta.repositories.SaleRepository;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

@Service
public class SmsService {

	@Value("${twilio.sid}")
	private String twilioSid;

	@Value("${twilio.key}")
	private String twilioKey;

	@Value("${twilio.phone.from}")
	private String twilioPhoneFrom;

	@Value("${twilio.phone.to}")
	private String twilioPhoneTo;
	@Autowired
	private SaleRepository repo;

	public void sendSms(String msg) {

		Twilio.init(twilioSid, twilioKey);

		PhoneNumber to = new PhoneNumber(twilioPhoneTo);
		PhoneNumber from = new PhoneNumber(twilioPhoneFrom);

		Message message = Message.creator(to, from, msg).create();

		System.out.println(message.getSid());
	}

	public void sendSmsId(Long saleId) {

		Sale sale = repo.findById(saleId).get();

		String date = sale.getDate().getMonthValue() + "/" + sale.getDate().getYear();

		Integer mes = sale.getDate().getMonthValue();
		String msgmes = "";

		switch (mes) {

		case 1:
			msgmes = "janeiro";
			break;
		case 2:
			msgmes = "fevereiro";break;
		case 3:
			msgmes = "março";break;
		case 4:
			msgmes = "abril";break;
		case 5:
			msgmes = "maio";break;
		case 6:
			msgmes = "junho";break;
		case 7:
			msgmes = "julho";break;
		case 8:
			msgmes = "agosto";break;
		case 9:
			msgmes = "setembro";break;
		case 10:
			msgmes = "outubro";break;
		case 11:
			msgmes = "novembro";break;
		case 12:
			msgmes = "dezembro";break;

		}

		String msg = "O vendedor " + sale.getSellerName();
		msg += " foi destaque em " + msgmes + " vendendo R$ " + String.format("%.2f", sale.getAmount())
		+ " com "+ sale.getDeals() + " vendas ";

		Twilio.init(twilioSid, twilioKey);

		PhoneNumber to = new PhoneNumber(twilioPhoneTo);
		PhoneNumber from = new PhoneNumber(twilioPhoneFrom);

		Message message = Message.creator(to, from, msg ).create();

		System.out.println(message.getSid());
		System.out.println(msg);
	}
}