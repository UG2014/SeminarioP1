class GroupController < ApplicationController
  def index
   @group = Group.all 
   respond_to do |format|
	format.json { render json: @group}
	end
  end

  def show
    @ShowGroup = Group.find(params[:id])
    respond_to do |format|
    format.json { render json: @ShowGroup}
	end
  end

  def create
  @NewTeam = Group.new
    @NewTeam.name_group = params[:name_group]
   
    @NewTeam.save
    respond_to do |format|
           format.json { render json: @NewTeam}
        
  end
  end

  def update
    @UpdateGroup = Team.find(params[:id])  
    if @UpdateGroup.update_attributes(params[:name_group])  
      flash[:notice] = "Successfully updated name_group."  
    end  
  respond_to do |format|
  
        format.json { render json: @UpdateGroup} 
  end
  end

  def delete
     @DeleteTeam = Group.find(params[:id])
    @DeleteTeam.destroy

    respond_to do |format|

      format.json { head :no_content}
  end
end
end
