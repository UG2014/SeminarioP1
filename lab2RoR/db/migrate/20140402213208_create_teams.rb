class CreateTeams < ActiveRecord::Migration
  def change
    create_table :teams do |t|
      t.string :name
      t.string :coach
      t.url :flag
      t.url :uniforme
      t.string :description

      t.timestamps
    end
  end
end
