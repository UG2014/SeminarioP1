class Team < ActiveRecord::Base
has_one :participation
has_one :groups, :through => :participation
has_many :matches
	validates :name_team, :presence => true
	validates :name_coach, :presence => true
	validates :flag_team, :presence => true
	validates :uniform, :presence => true
	validates :description, :presence => true
	validates :name_team, :format => { :with => /\A[a-zA-Z]+\z/, :message => "Pais Invalido" }
	validates :flag_team, :format => { :with => /^(http|https):\/\/[a-z0-9]+([\-\.]{1}[a-z0-9]+)*\.[a-z]{2,5}(:[0-9]{1,5})?(\/.*)?$/ix, :message => "Bandera Invalido", :multiline => true }
	validates :uniform, :format => { :with => /^(http|https):\/\/[a-z0-9]+([\-\.]{1}[a-z0-9]+)*\.[a-z]{2,5}(:[0-9]{1,5})?(\/.*)?$/ix, :message => "Uniforme Invalido", :multiline => true }
	validates :description, :length => { :in => 15..200 }
	validates :name_coach, :uniqueness => true
	validates :name_team, :uniqueness => true
end
