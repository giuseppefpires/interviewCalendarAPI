package com.xgeeks.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

@Entity
public class Availability {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@JsonIgnore
	private long id;
	
	private float startTime;
	
	private float endTime;
	
	@JsonFormat(pattern = "dd/MM/yyyy")
	@JsonSerialize(using = LocalDateSerializer.class) 
	private LocalDate date;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public float getStartTime() {
		return startTime;
	}

	public void setStartTime(float startTime) {
		this.startTime = startTime;
	}

	public float getEndTime() {
		return endTime;
	}

	public void setEndTime(float endTime) {
		this.endTime = endTime;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + Float.floatToIntBits(endTime);
		result = prime * result + Float.floatToIntBits(startTime);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Availability other = (Availability) obj;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (Float.floatToIntBits(endTime) != Float.floatToIntBits(other.endTime))
			return false;
		if (Float.floatToIntBits(startTime) != Float.floatToIntBits(other.startTime))
			return false;
		return true;
	}


	public static class Builder {
		
		private float startTime;
		private float endTime;
		private LocalDate date;
		
		public Builder startTime(float startTime) {
			this.startTime = startTime;
			return this;
		}
		
		public Builder endTime(float endTime) {
			this.endTime = endTime;
			return this;
		}
		
		public Builder date(LocalDate date) {
			this.date = date;
			return this;
		}
		
		public Availability build() {
			Availability availability = new Availability();
			availability.startTime = this.startTime;
			availability.endTime = this.endTime;
			availability.date = this.date;
			
			return availability;
		}

		

	}
	
}
