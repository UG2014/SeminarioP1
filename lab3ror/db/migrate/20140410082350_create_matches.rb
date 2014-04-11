class CreateMatches < ActiveRecord::Migration
  def change
    create_table :matches do |t|
      t.date :game_date
      t.integer :phase
      t.integer :state
      t.integer :teamL_id
      t.integer :teamV_id
      t.string :result
      t.integer :teamWind
      t.integer :teamL
      t.boolean :draw
      t.integer :group_id
      t.integer :stadium_id

      t.timestamps
    end
  end
end
