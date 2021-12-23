package br.com.vivo.orderproducer.domain.dto;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.vivo.orderproducer.domain.StatusEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderDto {
	
	private Long id;
	@NotNull
	@NotBlank(message = "name field cannot be empty")
	private String name;
	@NotNull
	@NotBlank(message = "description field cannot be empty")
	private String description;
	@NotNull
	private Double total;
	@NotNull
	@Valid
	private StatusEnum status;
}
