class Stadium < ActiveRecord::Base
	has_many :matches
	validates :name_stadium, :presence => true
	validates :city_stadium, :presence => true
	validates :costruction_date, :presence => true
	validates :max_capacity, :presence => true
	validates :picture_stadium, :presence => true

	validates :name_stadium, :uniqueness => true
	validates :city_stadium, :uniqueness => true
	validates :max_capacity, :numericality => { :only_integer => true, :greater_than_or_equal_to => 0 }	

	validates :city_stadium, :inclusion => { :in => %w(Belo\ Horizonte Brasilia Curitiba Fortaleza Manaus Natal Recife Rio\ de\ Janeiro Salvador Sao\ Paulo),  :message => "%{value} no esta permitido" }


end
