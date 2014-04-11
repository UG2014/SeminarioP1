# encoding: UTF-8
# This file is auto-generated from the current state of the database. Instead
# of editing this file, please use the migrations feature of Active Record to
# incrementally modify your database, and then regenerate this schema definition.
#
# Note that this schema.rb definition is the authoritative source for your
# database schema. If you need to create the application database on another
# system, you should be using db:schema:load, not running all the migrations
# from scratch. The latter is a flawed and unsustainable approach (the more migrations
# you'll amass, the slower it'll run and the greater likelihood for issues).
#
# It's strongly recommended that you check this file into your version control system.

ActiveRecord::Schema.define(version: 20140410082350) do

  create_table "groups", force: true do |t|
    t.string   "name_group"
    t.datetime "created_at"
    t.datetime "updated_at"
  end

  create_table "matches", force: true do |t|
    t.date     "game_date"
    t.integer  "phase"
    t.integer  "state"
    t.integer  "teamL_id"
    t.integer  "teamV_id"
    t.string   "result"
    t.integer  "teamWind"
    t.integer  "teamL"
    t.boolean  "draw"
    t.integer  "group_id"
    t.integer  "stadium_id"
    t.datetime "created_at"
    t.datetime "updated_at"
  end

  create_table "stadia", force: true do |t|
    t.string   "name_stadium"
    t.string   "city_stadium"
    t.date     "costruction_date"
    t.integer  "max_capacity"
    t.string   "picture_stadium"
    t.datetime "created_at"
    t.datetime "updated_at"
  end

  create_table "teams", force: true do |t|
    t.string   "name_team"
    t.string   "name_coach"
    t.string   "flag_team"
    t.string   "uniform"
    t.string   "description"
    t.datetime "created_at"
    t.datetime "updated_at"
  end

end
