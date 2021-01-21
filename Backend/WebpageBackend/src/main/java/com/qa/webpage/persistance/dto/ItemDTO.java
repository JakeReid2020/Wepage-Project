package com.qa.webpage.persistance.dto;

public class ItemDTO {
	
		private Long Id;

		
		private String name;
		
		private String description;
		
		private Double price;

		public ItemDTO() {
			super();
		}

		public ItemDTO(Long id, String name, String description, Double price) {
			super();
			Id = id;
			this.name = name;
			this.description = description;
			this.price = price;
		}

		public Long getId() {
			return Id;
		}

		public void setId(Long id) {
			Id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getDescription() {
			return description;
		}

		public void setDescrition(String description) {
			this.description = description;
		}

		public Double getPrice() {
			return price;
		}

		public void setPrice(Double price) {
			this.price = price;
		}

		@Override
		public String toString() {
			return "Item [Id=" + Id + ", name=" + name + ", description=" + description + ", price=" + price + "]";
		}
}
