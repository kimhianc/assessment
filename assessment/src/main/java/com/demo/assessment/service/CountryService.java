package com.demo.assessment.service;

import java.util.List;
import java.util.Optional;

import org.hibernate.type.StandardBasicTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.demo.assessment.constant.DemoConstant;
import com.demo.assessment.entity.CountryEntity;
import com.demo.assessment.repository.CountryRepository;
import com.demo.assessment.request.CountryRequestBean;
import com.demo.assessment.response.CountryResponseBean;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class CountryService {

	@Autowired
	private CountryRepository countryRepository;

	@PersistenceContext
	private EntityManager entityManager;

	@SuppressWarnings("unchecked")
	public List<CountryResponseBean> list(CountryRequestBean countryRequestBean) {

		List<CountryResponseBean> results = entityManager.createNativeQuery(
				"select countryid as countryId,country_code as countryCode,country_name as countryName,iso_code as isoCode,status as status from tbl_country where status =:status order by country_name OFFSET :startidx ROWS FETCH NEXT :amtidx ROWS ONLY")
				.unwrap(org.hibernate.query.NativeQuery.class).setParameter("status", DemoConstant.STS_ACTIVE)
				.setParameter("startidx", countryRequestBean.getStartIdx())
				.setParameter("amtidx", countryRequestBean.getAmtIdx())
				.addScalar("countryId", StandardBasicTypes.INTEGER).addScalar("countryCode", StandardBasicTypes.STRING)
				.addScalar("countryName", StandardBasicTypes.STRING).addScalar("isoCode", StandardBasicTypes.INTEGER)
				.addScalar("status", StandardBasicTypes.CHARACTER).getResultList();
		return results;
	}

	public CountryEntity save(CountryRequestBean countryRequestBean) {

		CountryEntity countryEntity = new CountryEntity();

		countryEntity.setCountryCode(countryRequestBean.getCountryCode());
		countryEntity.setCountryName(countryRequestBean.getCountryName());
		countryEntity.setIsoCode(countryRequestBean.getIsoCode());
		countryEntity.setStatus(DemoConstant.STS_ACTIVE);

		return countryRepository.save(countryEntity);
	}

	public CountryEntity get(Integer id) {

		Optional<CountryEntity> countryOptional = countryRepository.findById(id);
		if (countryOptional.isEmpty()) {
			throw new ResponseStatusException(org.springframework.http.HttpStatus.NOT_FOUND, "Country not found");
		}
		return countryOptional.get();
	}

	public CountryEntity update(CountryRequestBean countryRequestBean) {
		Optional<CountryEntity> countryOptional = countryRepository.findById(countryRequestBean.getCountryId());
		if (countryOptional.isEmpty()) {
			throw new ResponseStatusException(org.springframework.http.HttpStatus.NOT_FOUND, "Country not found");
		}
		CountryEntity countryEntity = countryOptional.get();
		countryEntity.setCountryCode(countryRequestBean.getCountryCode());
		countryEntity.setCountryName(countryRequestBean.getCountryName());
		countryEntity.setIsoCode(countryRequestBean.getIsoCode());
		return countryRepository.save(countryEntity);
	}

	public void delete(Integer id) {
		Optional<CountryEntity> countryOptional = countryRepository.findById(id);
		if (countryOptional.isEmpty()) {
			throw new ResponseStatusException(org.springframework.http.HttpStatus.NOT_FOUND, "Country not found");
		}
		CountryEntity countryEntity = countryOptional.get();
		countryEntity.setStatus(DemoConstant.STS_DEACTIVATED);
		countryRepository.save(countryEntity);
	}

	public int geCountryCount() {
		int count = (int) entityManager.createNativeQuery("select count(*) from tbl_country where status =:status")
				.unwrap(org.hibernate.query.NativeQuery.class).setParameter("status", DemoConstant.STS_ACTIVE)
				.getSingleResult();
		return count;
	}
}
