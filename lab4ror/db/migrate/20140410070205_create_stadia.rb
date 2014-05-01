class CreateStadia < ActiveRecord::Migration
  def change
    create_table :stadia do |t|
      t.string :name_stadium
      t.string :city_stadium
      t.date :costruction_date
      t.integer :max_capacity
      t.string :picture_stadium

      t.timestamps
    end
  end
end
