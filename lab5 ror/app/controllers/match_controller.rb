class MatchController < ApplicationController

  def index
    @matches = Match.all
    respond_to do |format|
 
      format.json { render json: @matches}
    end
  end

  def show
  @ShowMatch = Match.find(params[:id])
    respond_to do |format|
    
      format.json { render json: @ShowMatch}
    end
  end

  def create
        @NewMatch= Match.new
    @NewMatch.game_date = params[:game_date]
    @NewMatch.phase = params[:phase]
    @NewMatch.state = params[:state]
    @NewMatch.teamL_id = params[:teamL_id]
    @NewMatch.teamV_id = params[:teamV_id]
	@NewMatch.result = params[:result]
    @NewMatch.teamWind = params[:teamWind]
    @NewMatch.teamL = params[:teamL]
    @NewMatch.draw = params[:draw]	
	@NewMatch.group_id = params[:group_id]	
	@NewMatch.stadium_id = params[:stadium_id]	
		
	
    @NewTeam.save
    respond_to do |format|
  
        format.json { render json: @NewTeam}
        
   end 
  end

  def update
  @UpdateMatch = Team.find(params[:id])  
    if @UpdateMatch.update_attributes(params[:game_date])  
      flash[:notice] = "Successfully updated game_date."  
    end  
  respond_to do |format|
  
        format.json { render json: @UpdateMatch} 
	end
  end

  def delete
    @DeleteTeam = Match.find(params[:id])
    @DeleteTeam.destroy

    respond_to do |format|
     
   
      format.json { head :no_content}
    
    end
  end
end
