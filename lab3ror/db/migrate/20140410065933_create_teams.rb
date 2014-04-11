class CreateTeams < ActiveRecord::Migration
  def change
    create_table :teams do |t|
      t.string :name_team
      t.string :name_coach
      t.string :flag_team
      t.string :uniform
      t.string :description

      t.timestamps
    end
  end
end
