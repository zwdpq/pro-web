function delFruit(fid){
    if (confirm('是否要删除该水果？')){
        window.location.href='delflu?fid='+fid;
    }
}

function page(pageNo){
    window.location.href='index?pageNo='+pageNo;
}