package com.kul.logistics.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

/**
 * @author Shane Paulus
 * <p>
 * Date Created: 12-Sep-2022
 */

@Data
@Entity
public class LocationLink {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(nullable = false)
	private String adjacentLocation;
	@Column(nullable = false)
	private Double distance;

}
