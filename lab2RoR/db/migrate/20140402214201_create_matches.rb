class CreateMatches < ActiveRecord::Migration
  def change
    create_table :matches do |t|
      t.date :game_date
      t.int :phase
      t.int :state
      t.reference :local_team_id
      t.reference :visit_team_id
      t.string :result
      t.string :win
      t.string :loser
      t.boolean :draw
      t.reference :group_id
      t.reference :stadium_id

      t.timestamps
    end
  end
end
