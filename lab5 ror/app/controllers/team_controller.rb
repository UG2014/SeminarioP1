class TeamController < ApplicationController
  def index
  @teams = Team.all 
  respond_to do |format|
	
      format.json { render json: @teams}
	end
  end

  def show
  @ShowTeam = Team.find(params[:id])
    respond_to do |format|
    format.json { render json: @ShowTeam}
  end
  end

  def create
      @NewTeam = Team.new
    @NewTeam.name_team = params[:name_team]
    @NewTeam.name_coach = params[:name_coach]
    @NewTeam.flag_team = params[:flag_team]
    @NewTeam.uniform = params[:uniform]
    @NewTeam.description = params[:description]
    @NewTeam.save
    respond_to do |format|
  
        format.json { render json: @NewTeam}
        
   end 
  end

  def update  
  @UpdateTeam = Team.find(params[:id])  
    if @UpdateTeam.update_attributes(params[:name_team])  
      flash[:notice] = "Successfully updated name_team."  
    end  
  respond_to do |format|
  
        format.json { render json: @UpdateTeam} 
	end
  end

  def delete
   @DeleteTeam = Team.find(params[:id])
    @DeleteTeam.destroy

    respond_to do |format|
     
      format.json { head :no_content}
  end
end
