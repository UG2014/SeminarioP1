class StadiumController < ApplicationController
def index
     @stadium = Stadium.all
    respond_to do |format|
        format.json { render json: @stadium}
    end
  end

  def show
    @ShowStadium = Stadium.find(params[:id])
    respond_to do |format|
    
      format.json { render json: @ShowStadium}
    end
  end

  def create
    @NewStadium = Stadium.new
    @NewStadium.name_stadium = params[:name_stadium]
    @NewStadium.city_stadium = params[:city_stadium]
    @NewStadium.costruction_date = params[:costruction_date]
    @NewStadium.max_capacity = params[:max_capacity]
    @NewStadium.picture_stadium = params[:picture_stadium]
    @NewStadium.save


    respond_to do |format|
  
        format.json { render json: @NewStadium}
  end
  end

  def update
  @UpdateStadium = Team.find(params[:id])  
    if @UpdateStadium.update_attributes(params[:name_stadium])  
      flash[:notice] = "Successfully updated name_stadium."  
    end  
  respond_to do |format|
  
        format.json { render json: @UpdateStadium} 
  end
  end

  def delete
      @DeleteStadium = Stadium.find(params[:id])
    @DeleteStadium.destroy

    respond_to do |format|
     
    
      format.json { head :no_content}
    
    end
  end
end
 
