package com.jgartdesign.dsmeta.services;

import com.jgartdesign.dsmeta.repositories.SaleRepository;

@Service
public class SaleService {

	@Autowired
	private SaleRepository repo;

	@Transactional(readOnly=true)
	public Page<Sale> findAll(Pageable pageable){
	Page<Sale> result = repo.findAll(pageable);
	Page<Sale> page = result.map(x -> new Sale);
	return page;
	
}

	@Transactional(readOnly = true)
	public List<Sale> findSales() {

	}

}
