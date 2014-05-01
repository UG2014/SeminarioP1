class CreateGroups < ActiveRecord::Migration
  def change
    create_table :groups do |t|
      t.string :name_group

      t.timestamps
    end
  end
end
