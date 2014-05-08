class CreateParticipations < ActiveRecord::Migration
  def change
    create_table :participations do |t|
      t.integer :team_id
      t.integer :group_id
      t.integer :pts
      t.integer :matchW
      t.integer :matchL
      t.integer :matchD
      t.integer :goalF
      t.integer :goalC

      t.timestamps
    end
  end
end
