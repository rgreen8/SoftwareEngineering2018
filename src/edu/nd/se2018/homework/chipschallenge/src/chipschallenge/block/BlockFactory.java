package chipschallenge.block;

import chipschallenge.block.Block;

public class BlockFactory {

	public Block createBlock(String type) {
		
		if(type.equals("impass")){
			return new ImpassBlock();
		}
		if(type.equals("locked")){
			return new LockedBlock();
		}
		if(type.equals("victory")){
			return new VictoryBlock();
		}
		if(type.equals("walk")){
			return new WalkBlock();
		}
		if(type.equals("key")){
			return new KeyBlock();
		}
		return null;
		
	}

}
