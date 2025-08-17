#include <iostream>
#include <string>
#include <vector>

using namespace std;

int check[1000001] = {0, };
vector<int> answer(4);

vector<int> solution(vector<vector<int>> edges) {
    
    vector<vector<pair<int, int>>> graph(edges.size() * 2 + 1);
    int start;
    
    for (int i = 0; i < edges.size(); i++) {
        graph[edges[i][0]].push_back({edges[i][1], 1});
        if (edges[i][0] != edges[i][1]) graph[edges[i][1]].push_back({edges[i][0], -1});
    }
    
//정점 찾기
    for (int i = 1; i < graph.size(); i++) {
        int plus = 0;
        int minus = 0;
        for (int j = 0; j < graph[i].size(); j++) {
            (graph[i][j].second == 1) ? plus++ : minus++;
        }
        if (plus >= 2 && minus == 0) {
            start = i;
            break;
        }
    }
    
    for (int i = 0; i < graph[start].size(); i++) {
        for (auto it = graph[graph[start][i].first].begin(); it != graph[graph[start][i].first].end(); ++it) {
            if (it->first == start) {
            graph[graph[start][i].first].erase(it);
            break;              
        }
    }

    }
    
    answer[0] = start;
    
    for (int i = 0; i < graph[start].size(); i++) {
        int st = graph[start][i].first;
        int u = st;
        while(true) {

            if ((graph[u].size() == 1 && graph[u][0].first != u) || (graph[u].size() == 0)) {
                answer[2]++;
                break;
            }
            else if (graph[u].size() == 4) {
                answer[3]++;
                break;
            }
            else if (graph[u].size() == 2){
                graph[u][0].second == 1 ? u = graph[u][0].first : u = graph[u][1].first;
            }
            if (u == st) break;
        }
    }
    
    answer[1] = graph[start].size() - answer[2] - answer[3];

    return answer;
}