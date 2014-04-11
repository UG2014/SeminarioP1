class Group < ActiveRecord::Base
		validates :name_group, presence: true
		 validates :name_group, uniqueness: true
		 validates :name_group, length: 
						{in: 1..1}
		 validates :name_group, format: { with: /\A[a-zA-Z]+\z/,
    message: "only allows letters" }

end
