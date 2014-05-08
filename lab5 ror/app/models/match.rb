class Match < ActiveRecord::Base
belongs_to :localteam, :class_name => "Match", foreign_key => "teamL_id"
belongs_to :visitteam, :class_name => "Match", foreign_key => "teamV_id"
belongs_to :stadium, :class_name => "Match", foreign_key => "stadium_id"
belongs_to :winner, :class_name => "Match"
belongs_to :losser, :class_name => "Match"
belongs_to :group, :class_name => "Match", foreign_key => "group_id"

validates :game_date, :presence => true
validates :game_date, :uniqueness => true
validates :phase, :presence => true
validates :state, :presence => true
validates :localteam_id, :presence => true
validates :visitteam_id, :presence => true

validates :stage, numericality: {only_integer: true, greater_than_or_equal_to: 1, less_than_or_equal_to: 6}

validates :stadium_id, :presence => true


end
