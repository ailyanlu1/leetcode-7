class LRUCache:    
    # @param capacity, an integer
    def __init__(self, capacity):
        self.myDic={};
        self.capacity=capacity;
        self.capacity_used=0;
        self.queue=[];
        pass;
 
    # @return an integer
    def get(self, key):
        if(key in self.myDic):
            self.queue.remove(key);
            self.queue.append(key);
            return self.myDic[key];
        else:return -1;
 
    # @param key, an integer
    # @param value, an integer
    # @return nothing
    def set(self, key, value):
        if(key in self.myDic):
            self.myDic[key]=value;
            self.get(key)
        else:
            if(self.capacity_used+1>self.capacity):
                del self.myDic[self.queue[0]];
                del self.queue[0];
            else:
                self.capacity_used+=1;
            self.myDic[key]=value;
            self.queue.append(key);